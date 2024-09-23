resource "kubernetes_service" "host_service" {
  metadata {
    name      = "host"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
    labels = {
      app = "host"
    }
  }

  spec {
    type = "LoadBalancer"
    selector = {
      app = "host"
    }

    port {
      name        = "http"
      port        = 8090
      target_port = 8090
    }
  }
}

resource "kubernetes_deployment" "host_deployment" {
  metadata {
    name      = "host"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
    labels = {
      app = "host"
    }
  }

  spec {

    replicas = "2"

    selector {
      match_labels = {
        app = "host"
      }
    }

    template {
      metadata {
        labels = {
          app = "host"
        }
      }

      spec {
        container {
          name              = "host"
          image             = "qwerty2137/host:latest"
          image_pull_policy = "Always"

          port {
            name           = "http"
            container_port = 8090
          }

          resources {
            limits = {
              cpu    = 0.4
              memory = "400Mi"
            }
          }
        }
      }
    }
  }
}

resource "kubernetes_ingress_v1" "host_ingress" {
  metadata {
    name      = "host-ingress"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
  }

  spec {
    rule {
      host = "host.com"
      http {
        path {
          path      = "/*"
          path_type = "Prefix"
          backend {
            service {
              name = kubernetes_service.host_service.metadata.0.name
              port {
                number = 90
              }
            }
          }
        }
      }
    }
  }
}