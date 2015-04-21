all:
	javac -cp .:jewelcli-0.7.6.jar:antlr-4.5-complete.jar  -d bin src/fr/enslyon/Main.java -sourcepath src

