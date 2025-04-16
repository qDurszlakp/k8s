#!/bin/bash

# Script to wait for Kafka to be ready and create defined topics.

BOOTSTRAP_SERVER="kafka:9092"
MAX_RETRIES=10
SLEEP_INTERVAL=15

echo "Waiting for Kafka ($BOOTSTRAP_SERVER) to be ready..."

# Wait for Kafka using kafka-topics.sh --list
kafka_ready=false
retry_count=0
while [ $retry_count -lt $MAX_RETRIES ]; do
  if /opt/bitnami/kafka/bin/kafka-topics.sh --bootstrap-server $BOOTSTRAP_SERVER --list > /dev/null 2>&1; then
    echo "Kafka is ready!"
    kafka_ready=true
    break
  else
    echo "Kafka not ready yet. Retrying in $SLEEP_INTERVAL seconds... ($((retry_count+1))/$MAX_RETRIES)"
    sleep $SLEEP_INTERVAL
    retry_count=$((retry_count+1))
  fi
done

if [ "$kafka_ready" = false ]; then
  echo "Kafka did not become ready in time after $MAX_RETRIES retries. Exiting."
  exit 1
fi

echo "Creating topic '$TOPIC_NAME'..."

# Create my topic

TOPIC_1_NAME="TOPIC_1"
TOPIC_1_PARTITIONS=1
TOPIC_1_REPLICATION_FACTOR=1

/opt/bitnami/kafka/bin/kafka-topics.sh --create \
  --topic $TOPIC_1_NAME \
  --bootstrap-server $BOOTSTRAP_SERVER \
  --partitions $TOPIC_1_PARTITIONS \
  --replication-factor $TOPIC_1_REPLICATION_FACTOR

echo "Topic creation finished."
exit 0