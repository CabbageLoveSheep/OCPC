@echo off
set current_path="%cd%"
cd %current_path%
call mvn clean package -P demo -DskipTests -U
pause