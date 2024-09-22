# resource "kubernetes_service" "demo_app_postgres_service" {
#   metadata {
#     name = "demo-app-postgres"
#     namespace = kubernetes_namespace.demo_app_ns.metadata.0.name
#     labels = {
#       app = "demo-app"
#     }
#   }
#
#   spec {
#     type = "NodePort"
#     selector = {
#       app = kubernetes_deployment.demo_app_postgres_deployment.metadata.0.labels.app
#       tier = "postgres"
#     }
#
#     port {
#       port = 5432
#       target_port = 5432
#       node_port = 30007
#     }
#
#   }
# }
#
# resource "kubernetes_persistent_volume_claim" "demo_app_pvc" {
#   metadata {
#     name = "postgres-pvc"
#     namespace = kubernetes_namespace.demo_app_ns.metadata.0.name
#     labels = {
#       app = "demo-app"
#     }
#   }
#
#   spec {
#     access_modes = [
#       "ReadWriteOnce"
#     ]
#
#     resources {
#       requests = {
#         storage = "1Gi"
#       }
#     }
#   }
# }
#
# resource "kubernetes_deployment" "demo_app_postgres_deployment" {
#   metadata {
#     name = "demo-app-postgres"
#     namespace = kubernetes_namespace.demo_app_ns.metadata.0.name
#     labels = {
#       app = "demo-app"
#     }
#   }
#
#   spec {
#     selector {
#       match_labels = {
#         app = "demo-app"
#         tier = "postgres"
#       }
#     }
#
#     strategy {
#       type = "Recreate"
#     }
#
#     template {
#       metadata {
#         labels = {
#           app = "demo-app"
#           tier = "postgres"
#         }
#       }
#
#       spec {
#         container {
#           image = "postgres:13"
#           name = "postgres"
#
#           env {
#             name = "POSTGRES_DB"
#             value_from {
#               config_map_key_ref {
#                 key = "postgres-database-name"
#                 name = kubernetes_config_map.demo_app_cm.metadata.0.name
#               }
#             }
#           }
#
#           env {
#             name = "POSTGRES_USER"
#             value_from {
#               config_map_key_ref {
#                 key = "postgres-user-username"
#                 name = kubernetes_config_map.demo_app_cm.metadata.0.name
#               }
#             }
#           }
#
#           env {
#             name = "POSTGRES_PASSWORD"
#             value_from {
#               secret_key_ref {
#                 key = "postgres-user-password"
#                 name = kubernetes_secret.demo_app_secret.metadata.0.name
#               }
#             }
#           }
#
#           liveness_probe {
#             tcp_socket {
#               port = 5432
#             }
#           }
#
#           port {
#             name = "postgres"
#             container_port = 5432
#           }
#
#           volume_mount {
#             name = "postgres-persistent-storage"
#             mount_path = "/var/lib/postgresql/data"
#           }
#         }
#
#         volume {
#           name = "postgres-persistent-storage"
#           persistent_volume_claim {
#             claim_name = kubernetes_persistent_volume_claim.demo_app_pvc.metadata.0.name
#           }
#         }
#       }
#     }
#   }
# }
