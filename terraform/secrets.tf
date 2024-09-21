# Create a secret for Postgres
resource "kubernetes_secret" "demo_app_secret" {
  metadata {
    name = "postgres-pass"
    namespace = kubernetes_namespace.demo_app_ns.metadata.0.name
  }

  data = {
    # Base64-encoded password
    postgres-user-password = "ZGV2"
  }
}

# Create a secret for docker registry
resource "kubernetes_secret" "docker_secret" {
  metadata {
    name = "docker-cfg"
    namespace = kubernetes_namespace.demo_app_ns.metadata.0.name
  }

  data = {
    ".dockerconfigjson" = jsonencode({
      auths = {
        "${var.registry_server}" = {
          auth = "${base64encode("${var.registry_username}:${var.registry_password}")}"
        }
      }
    })
  }

  type = "kubernetes.io/dockerconfigjson"
}