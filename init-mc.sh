#!/bin/sh

# Configure mc alias
mc alias set myminio http://minio:9000 "${MINIO_ROOT_USER}" "${MINIO_ROOT_PASSWORD}"

# Create bucket if not exists
mc mb myminio/"${MINIO_BUCKETNAME}" || true
