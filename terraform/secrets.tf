# Create a secret for db
resource "kubernetes_secret" "db_secret" {
  metadata {
    name = "db-pass"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
  }

  data = {
    # Base64-encoded password
    db-password = "ZGV2"
  }
}

# Secret for docker registry
resource "kubernetes_secret" "docker_secret" {
  metadata {
    name = "docker-cfg"
    namespace = kubernetes_namespace.sandbox_ns.metadata.0.name
  }

  data = {
    ".dockerconfigjson" = jsonencode({
      auths = {
        (var.registry_server) = {
          auth = base64encode("${var.registry_username}:${var.registry_password}")
        }
      }
    })
  }

  type = "kubernetes.io/dockerconfigjson"
}