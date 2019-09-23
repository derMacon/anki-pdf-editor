#!/bin/sh

# Removes all temp files that arent of the listed types
rm -v !(*.tex|*.pdf|*.md|*.sh)

