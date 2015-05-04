all:
	mkdir -p out
	javac -cp .:antlr-4.5-complete.jar  -d bin src/fr/enslyon/Main.java -sourcepath src

