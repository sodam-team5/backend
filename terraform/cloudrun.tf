resource "google_cloud_run_service" "default" {
  name     = local.service_name
  location = local.region

  template {
    spec {
      service_account_name = local.cloudrun_sa_email
      containers {
        image = local.image
        ports {
          container_port = 8080
        }

        env {
          name  = "DB_HOST"
          value = local.db_host
        }
        env {
          name  = "DB_NAME"
          value = local.db_name
        }
        env {
          name  = "DB_USER"
          value = local.db_user
        }
        env {
          name  = "DB_PASSWORD"
          value = local.db_password
        }
        env {
          name = "GOOGLE_CLOUD_PROJECT"
          value = local.project_id
        }
      }
    }
  }
}

