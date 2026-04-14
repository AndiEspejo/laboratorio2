#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# shellcheck source=./use-java11.sh
source "$SCRIPT_DIR/use-java11.sh"

cd "$SCRIPT_DIR"
./mvnw -B package -DskipTests --file pom.xml
