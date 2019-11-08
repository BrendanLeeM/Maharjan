var rs = require('readline-sync');

var action = rs.question('Enter the action{+,-,*,/,%,&,|,>>,<<,~} ');
var result;

if (action == '+' || action == '-' || action == '*' || action == '%' || action == '/') {
var fNum1 = rs.question('1st Binary Number: ');
var fNum2 = rs.question('2nd Binary Number: ');

	fNum1 = parseInt(fNum1,2);
	fNum2 = parseInt(fNum2,2);

	result = fNum1 + action + fNum2;
	result = eval(result);
 	result = result.toString(2);
} 
else if(action == '<<' || action == '>>'){
	var bNum = rs.question('Enter number to shift: ');
	if(action == '<<')
 		result = bNum + '0';
	else{
		result = bNum.substring(0,bNum.length - 1);
	}
}
else if(action == '&'){
	var bNum1 = rs.question('Enter first binary: ');
	var bNum2 = rs.question('Enter second binary: ');
	bNum1 = bNum1.split("");
	bNum2 = bNum2.split("");
	bNum1 = bNum1.reverse();
	bNum2 = bNum2.reverse();
	bNum1 = bNum1.join("");
	bNum2 = bNum2.join("");

	if(bNum1.length > bNum2.length){
		length = bNum1.length;
	}
	else {
		length = bNum2.length;
	}

	result = [];

	for(i = 0;i < length;i++){
		result.push(0);
	}
	for(i = 0;i < length;i++){
			if(i < bNum1.length && i < bNum2.length){
			if (bNum1.charAt(i) == '1' && bNum2.charAt(i) == '1') {
				result[i] = 1;
			}
		}
	}
		result = result.reverse();
		result = result.join("");
	}

else if(action == '|'){
	var bNum1 = rs.question('Enter first binary: ');
	var bNum2 = rs.question('Enter second binary: ');
	bNum1 = bNum1.split("");
	bNum2 = bNum2.split("");
	bNum1 = bNum1.reverse();
	bNum2 = bNum2.reverse();
	bNum1 = bNum1.join("");
	bNum2 = bNum2.join("");

	if(bNum1.length > bNum2.length){
		length = bNum1.length;
	}
	else {
		length = bNum2.length;
	}

	result = [];

	for(i = 0;i < length;i++){
		result.push(0);
	}
	for(i = 0;i < length;i++){
			if(i < bNum1.length){
			if (bNum1.charAt(i) == '1') {
				result[i] = 1;
			}
		}
			if (i < bNum2.length) {
				if (bNum2.charAt(i) == '1') {
					result[i] = 1;
				}
			}
	}
		result = result.reverse();
		result = result.join("");
	}
else if (action == '~') {
	var bNum = rs.question('Enter binary: ');
	
	result = [];
	for (var i = 0; i < bNum.length; i++) {
	 	if (bNum.charAt(i) == 1) {
	 		result.push(0);
	 	}
	 	else {
	 		result.push(1);
	 	}
	}
	result = result.join("");
} 
else {
	console.log("incorrect input");
}

console.log(result);
