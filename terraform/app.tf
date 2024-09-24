resource "kubernetes_service" "app_service" {
  metadata {
    name      = "app"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
    labels = {
      app = "app"
    }
  }

  spec {
    type = "LoadBalancer"
    selector = {
      app = "app"
    }

    port {
      name        = "http"
      port        = 8080
      target_port = 8080
    }
  }
}

resource "kubernetes_deployment" "app_deployment" {
  metadata {
    name      = "app"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
    labels = {
      app = "app"
    }
  }

  spec {

    replicas = "2"

    selector {
      match_labels = {
        app = "app"
      }
    }

    template {
      metadata {
        labels = {
          app = "app"
        }
      }

      spec {
        container {
          name              = "app"
          image             = "qwerty2137/app:latest"
          image_pull_policy = "Always"

          port {
            name           = "http"
            container_port = 8080
          }

          resources {
            limits = {
              cpu    = 0.4
              memory = "400Mi"
            }
          }

          env {
            name  = "JAVA_OPTS"
            value = "-Dspring.profiles.active=deployment"
          }

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
        }
      }
    }
  }
}

resource "kubernetes_ingress_v1" "app_ingress" {
  metadata {
    name      = "app-ingress"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
  }

  spec {
    rule {
      host = "app.com"
      http {
        path {
          path      = "/*"
          path_type = "Prefix"
          backend {
            service {
              name = kubernetes_service.app_service.metadata.0.name
              port {
                number = 80
              }
            }
          }
        }
      }
    }
  }
}