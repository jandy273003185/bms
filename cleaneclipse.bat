@echo On
@Rem É¾³ýEclipse MavenÏîÄ¿
@PROMPT [Com]

@for /r . %%a in (.) do @if exist "%%a/.settings" rd /s /q "%%a/.settings"
@Rem for /r . %%a in (.) do @if exist "%%a/.settings" @echo "%%a/.settings"

@for /r . %%a in (.) do @if exist "%%a/target" rd /s /q "%%a/target"
@Rem for /r . %%a in (.) do @if exist "%%a/target" @echo "%%a/target"

@del /a /f /s /q  ".project" ".classpath" ".factorypath"
@del /a /f /s /q  ".project" ".classpath"  ".factorypath"

@echo Mission Completed.
@pause