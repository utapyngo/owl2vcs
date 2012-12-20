@ECHO OFF
for /f %%a in ('"%~dp0javamem.cmd"') do set mem=%%a
java -Xmx%mem%m -jar "%~dp$PATH:0owl2vcs.jar" owl2vcs.tools.VisualMerge %*
