variable "registry_server" {
  type = string
  default = "https://hub.docker.com/"
}

variable "registry_username" {
  type = string
  default = "qwerty2137"
}

variable "registry_password" {
  type = string
  sensitive = true
}