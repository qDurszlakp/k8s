resource "kubernetes_config_map" "demo_app_cm" {
  metadata {
    name = "postgres-config-map"
    namespace = kubernetes_namespace.demo_app_ns.metadata.0.name
  }

  data = {
    postgres-server = "demo-app-postgres"
    postgres-database-name = "dev"
    postgres-user-username = "dev_user"
  }
}
