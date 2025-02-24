locals {
  project_id   = "sodam-450208"
  region       = "asia-northeast3"
  service_name = "sodam-cloudrun"
  image        = "asia-northeast3-docker.pkg.dev/sodam-450208/sodam-registry/backend-image:latest"
  db_host      = "34.47.84.224"
  db_name      = "sodam-db"
  db_user      = "root"
  db_password  = "sodam"
  cloudrun_sa_email = "sodam-cloudrun-sa@sodam-450208.iam.gserviceaccount.com"
}
