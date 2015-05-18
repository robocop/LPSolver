all:
	mkdir -p bin
<<<<<<< HEAD
<<<<<<< HEAD
=======
	chmod +x toto
	chmod +x totoopt
	chmod +x totoprint
>>>>>>> review2
=======
	chmod +x toto
	chmod +x totoopt
	chmod +x totoprint
>>>>>>> da6124ad5cb995fcf7cf876ae6b7e2ddbfda3861
	javac -cp .:antlr-4.5-complete.jar  -d bin src/fr/enslyon/Main.java -sourcepath src

