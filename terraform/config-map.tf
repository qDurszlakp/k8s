resource "kubernetes_config_map" "sandbox_cm" {
  metadata {
    name = "db-config-map"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
  }

  data = {
    db-username = "dev_user"
    db-name = "sandbox_db"
  }
}
