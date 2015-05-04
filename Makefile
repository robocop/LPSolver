all:
	mkdir -p bin
	javac -cp .:antlr-4.5-complete.jar  -d bin src/fr/enslyon/Main.java -sourcepath src

