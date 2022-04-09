#Entering values ​​into the program
.data
var:
	a: .word 100
	b: .word 500
	c: .word 300
	d: .word 50
	e: .word 25
	f: .word 10
#Finished entering values ​​into the program

#Defining fields to values
.text

lw $s0,a
lw $s1,b
lw $s2,c
lw $s3,d
lw $s4,e
lw $s5,f
#Finished defining fields to values

#Processes data
#Sum 100 + 500 = 600
add $t0,$s0,$s1
#Subtract 300 - 50 = 250
sub $t1,$s2,$s3
#Subtract 600 - 250 = 350
sub $t2,$t0,$t1
#Subtract 350 - 25 = 325
sub $t3,$t2,$s4
#Sum 325 + 10 = 335
add $t4,$t3,$s5

#Final step print screen
li $v0, 1
add $a0, $zero, $t4
syscall




