#!/bin/sh

# Subversion provides the paths we need as the ninth, tenth, and eleventh 
# parameters.

owl2merge ${10} ${9} ${11} -o STDOUT --auto
