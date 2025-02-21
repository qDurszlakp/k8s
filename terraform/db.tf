resource "kubernetes_service" "db_service" {
  metadata {
    name = "db"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
    labels = {
      app = "db"
    }
  }

  spec {
    type = "NodePort"
    selector = {
      app = "db"
      tier = "postgres"
    }

    port {
      port = 5432
      target_port = 5432
      node_port = 30007
    }
  }
}

resource "kubernetes_persistent_volume_claim" "db_pvc" {
  metadata {
    name = "db-pvc"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
    labels = {
      app = "db"
    }
  }

  spec {
    access_modes = [
      "ReadWriteOnce"
    ]

    resources {
      requests = {
        storage = "1Gi"
      }
    }
  }
}

resource "kubernetes_deployment" "db_deployment" {
  metadata {
    name = "db"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
    labels = {
      app = "db"
    }
  }

  spec {
    selector {
      match_labels = {
        app = "db"
        tier = "postgres"
      }
    }

    strategy {
      type = "Recreate"
    }

    template {
      metadata {
        labels = {
          app = "db"
          tier = "postgres"
        }
      }

      spec {
        container {
          image = "postgres:17"
          name = "postgres"

          env {
            name = "POSTGRES_DB"
            value_from {
              config_map_key_ref {
                key = "db-name"
                name = kubernetes_config_map.sandbox_cm.metadata.0.name
              }
            }
          }

          env {
            name = "POSTGRES_USER"
            value_from {
              config_map_key_ref {
                key = "db-username"
                name = kubernetes_config_map.sandbox_cm.metadata.0.name
              }
            }
          }

          env {
            name = "POSTGRES_PASSWORD"
            value_from {
              secret_key_ref {
                key = "db-password"
                name = kubernetes_secret.db_secret.metadata.0.name
              }
            }
          }

          liveness_probe {
            tcp_socket {
              port = 5432
            }
          }

          port {
            name = "postgres"
            container_port = 5432
          }

          volume_mount {
            name = "db-persistent-storage"
            mount_path = "/var/lib/postgresql/data"
          }
        }

        volume {
          name = "db-persistent-storage"
          persistent_volume_claim {
            claim_name = kubernetes_persistent_volume_claim.db_pvc.metadata.0.name
          }
        }
      }
    }
  }
}
