#!/bin/zsh
./gradlew :llvm:run -Djna.library.path=`llvm-config --libdir`