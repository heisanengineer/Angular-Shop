.data

myArray: .mySpace 100
Ask: .asciiz 
myTask: .asciiz
minValue: .asciiz
maxvalue: .asciiz
myDisplay: .asciiz
mySpace: .asciiz
myNextLine: .asciiz

.text
    .globl mainClass

mainClass:
    	la $a0, myDisplay             	
    	li $v0, 4
    	syscall

    	li $v0, 5               	
    	syscall
    	move $t1,$v0            	

    	la $t0, myArray           	
    	li $t2, 0               	
    	lw $t3,($t0)            	
    	lw $t4,($t0)            	


loopWhile:
    	la $a0, myTask          	
    	li $v0, 4
    	syscall

    	li $v0, 5               	
    	syscall
    	sw $v0, ($t0)           	


end:
	add $t0, $t0, 4         	
   	add $t2, $t2, 1         	
    	blt $t2, $t1,loopWhile      	

endw:
    	la $a0,myDisplay          	
    	li $v0,4            
    	syscall

    	li $t0, 0               	
    	li $t2, 0                	
    	la $t0, myArray            	

 sprint:
    	lw $t6,($t0)            	
    	move $a0, $t6           	
    	li $v0, 1               	
    	syscall

    	la $a0,mySpace            	
    	li $v0,4            
    	syscall

    	add $t0, $t0, 4         	
    	add $t2, $t2, 1         	

    	blt $t2, $t1,sprint     	

    	li $t2, 0
    	la $t0, myArray
    	add $t0, $t0, 4
    	add $t2, $t2, 1


 loop:  
 	lw $t8,($t0)             	
   	bge $t8, $t3, notMin     	
   	move $t3,$t8             	
    	j notMax

 notMin: 
 	ble $t8,$t4, notMax         	
   	move $t4,$t8             	

 notMax:    
 	add $t2,$t2,1            	
    	add $t0,$t0, 4           	
    	blt $t2, $t1,loop

 myPrint:
    	la $a0,maxvalue               	
    	li $v0,4            
    	syscall

    	move $a0, $t4            	
    	li $v0,1
    	syscall

    	li $v0,10                	
    	syscall     

