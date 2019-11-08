//create canvas to draw on
var canvas = document.getElementById("canvasId").getContext("2d");
canvas.strokeStyle ="#FF0000";
canvas.lineWidth = 5;
//buttons to move
var stopBtn = document.getElementById("stop");
var leftBtn = document.getElementById("left");
var rightBtn = document.getElementById("right");
//variables needed
var fromX = 0;
var fromY = 306;
var incX = 1;
var incY = 0;
var x = 1;
var y = 306;
var startFlag = 0;
var interval;

stopBtn.onclick = function () {
	if(startFlag == 1){
	clearInterval(interval)
}
else {
	startFlag = 1;
	document.getElementById("stop").value = "stop";

	interval = setInterval(function (){ 
	if (x + incY < 1200 && x + incX > 0 && y + incY < 612 && y + incY > 0) {
	canvas.moveTo(fromX,fromY);
	x = x + incX;
	y = y + incY;
	canvas.lineTo(x,y);
	canvas.stroke();
	}
} ,100);

	}
}


leftBtn.onclick = function () {
switch(incX){
	case 1: 
	incX = 0;
	incY = -1;
	fromX = x - 1;
	fromY = y - 1;
	break;
	case -1:
	incX = 0;
	incY = 1;
	fromX = x - 1;
	fromY = y - 1;
	break;
	case 0:
	if(incY == -1){
		incX = -1;
		incY = 0;
		fromX = x - 1;
		fromY = y - 1;
	}
	else {
		incX = 1;
		incY = 0;
		fromX = x - 1;
		fromY = y - 1;
	}

}
}
rightBtn.onclick = function () {
switch(incX){
	case 1: 
	incX = 0;
	incY = 1;
	fromX = x - 1;
	fromY = y - 1;
	break;
	case -1:
	incX = 0;
	incY = -1;
	fromX = x - 1;
	fromY = y - 1;
	break;
	case 0:
	if(incY == -1){
		incX = 1;
		incY = 0;
		fromX = x - 1;
		fromY = y - 1;
	}
	else {
		incX = -1;
		incY = 0;
		fromX = x - 1;
		fromY = y - 1;
	}

}
}
