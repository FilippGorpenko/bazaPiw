#!/usr/bin/env bash

docker run -it -u=$(id -u) -v $(pwd):/documents/ asciidoctor/docker-asciidoctor asciidoctor-pdf -a allow-uri-read -r asciidoctor-diagram README.adoc
