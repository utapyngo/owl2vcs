@for /f %%a in ('"%~dp$PATH:0javamem.cmd"') do @set mem=%%a
java -Xmx%mem%m -jar "%~dp$PATH:0owl2vcs.jar" owl2vcs.tools.Diff %*
