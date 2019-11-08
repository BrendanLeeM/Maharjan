//using the template for calc 
var Calc = {
//store necessary data in the model
Model : {
  oldVal : undefined,
  operator : undefined,
  newVal : undefined,
  memoryVal : 0,
  highlighted : []
},

//buttons
View : {
  textRow : {id: "textRow", type: "text", value: "0", onclick:""},
  button0 : {id: "button0", type: "button", value: 0, onclick:""},
  buttonSub : {id: "buttonSub", type: "button", value: "-", onclick:""},
  buttonAdd : {id: "buttonAdd", type: "button", value: "+", onclick:""},
  buttonDiv : {id: "buttonDiv", type: "button", value: "/", onclick:""},
  buttonMulti : {id: "buttonMulti", type: "button", value: "*", onclick:""},
  buttonDec : {id: "buttonDec", type: "button", value: ".", onclick:""},
  buttonEq : {id: "buttonEq", type: "button", value: "=", onclick:""},
  buttonClear : {id: "buttonClear", type: "button", value: "C", onclick:""},
  button9 : {id: "button9", type: "button", value: 9, onclick:""},
  buttonMR : {id: "buttonMR", type: "button", value: "MR", onclick:""},
  buttonMmin : {id: "buttonMmin", type: "button", value: "M-", onclick:""},
  buttonMAdd : {id: "buttonMAdd", type: "button", value: "M+", onclick:""},
  buttonMC : {id: "buttonMC", type: "button", value: "MC", onclick:""},
  button1 : {id: "button1", type: "button", value: 1, onclick:""},
  button2 : {id: "button2", type: "button", value: 2, onclick:""},
  button3 : {id: "button3", type: "button", value: 3, onclick:""},
  button4 : {id: "button4", type: "button", value: 4, onclick:""},
  button5 : {id: "button5", type: "button", value: 5, onclick:""},
  button6 : {id: "button6", type: "button", value: 6, onclick:""},
  button7 : {id: "button7", type: "button", value: 7, onclick:""},
  button8 : {id: "button8", type: "button", value: 8, onclick:""},

},
//controller handles when a button is pressed
Controller : {
buttonHandler : function(that) {
    
    //if entry is = show solution
    if (that.value == "=" && Calc.Model.newVal != undefined) {
      document.getElementById("textRow").value = Calc.equals(Calc.Model.oldVal,Calc.Model.operator,Calc.Model.newVal);    
      Calc.Model.oldVal = Calc.equals(Calc.Model.oldVal,Calc.Model.operator,Calc.Model.newVal);
      Calc.Model.operator = undefined;
      Calc.Model.newVal = undefined;
      //unhighlight buttons
      while(Calc.Model.highlighted.length != 0){
        Calc.Model.highlighted.pop().style.color = "";
      }
      }

      //do nothing if = is selected with no second value
      else if (that.value == "=" && Calc.Model.newVal == undefined) {

      }

    else if (that.value == "MR" || that.value == "MC" || that.value == "M-" || that.value == "M+" ) {
      switch(that.value){
        case "MR" : document.getElementById("textRow").value = Calc.Model.memoryVal;
        break;
        case "M+": Calc.Model.memoryVal = (Calc.Model.memoryVal*1) + (document.getElementById("textRow").value*1);
        break;
        case "M-": Calc.Model.memoryVal = (Calc.Model.memoryVal*1) - (document.getElementById("textRow").value*1); 
        break;
        case "MC" : Calc.Model.memoryVal = 0;
        break;
      }
    }
    //if entry is clear clear all
    else if (that.value == "C") {
      document.getElementById("textRow").value = 0;    
      Calc.Model.oldVal = undefined;
      Calc.Model.operator = undefined;
      Calc.Model.newVal = undefined;
      
      while(Calc.Model.highlighted.length != 0){
        Calc.Model.highlighted.pop().style.color = "";
      }

    }
  //if an operator has not been chosen we are still choosing the first number or choosing an operator
    else if (Calc.Model.operator == undefined) {
        //check if an operator is chosen and it is not the first button pressed
        if ((that.value == "+" || that.value == "-" || that.value == "*" || that.value == "/" ) && Calc.Model.oldVal != undefined) {
          that.style.color = "red";
          Calc.Model.operator = that.value;
          document.getElementById("textRow").value = Calc.Model.oldVal + " " + that.value;
          Calc.Model.highlighted.push(that);    
        }
        //if an operator is pressed first we want the value to not change
        else if ((that.value == "+" || that.value == "*" || that.value == "/" || that.value == "=") && Calc.Model.oldVal == undefined) {
         document.getElementById("textRow").value = 0; 
        }
        //look for negative sign
        else if (that.value == "-" && Calc.Model.oldVal == undefined) {
          that.style.color = "blue";
          Calc.Model.oldVal = that.value;
          document.getElementById("textRow").value = Calc.Model.oldVal;
          Calc.Model.highlighted.push(that);
                  
        }
        //if neither of these conditions are true we are simply choosing our first number, also check for decimals
        else if (that.value == "." && Calc.Model.oldVal == undefined) {
          that.style.color = "blue";
          Calc.Model.oldVal = 0 + that.value;
          document.getElementById("textRow").value = Calc.Model.oldVal; 
          Calc.Model.highlighted.push(that);
        }
        //if we have a previous number already keep adding to it
        else if (Calc.Model.oldVal != undefined) {
          that.style.color = "blue";
          Calc.Model.oldVal = Calc.Model.oldVal + that.value;
          document.getElementById("textRow").value = Calc.Model.oldVal; 
          Calc.Model.highlighted.push(that);
        }
        else {
          that.style.color = "blue";
          Calc.Model.oldVal = that.value;
          document.getElementById("textRow").value = Calc.Model.oldVal;
          Calc.Model.highlighted.push(that);
        }
    }

    else {
      //don't allow operators to be called
      if (!isNaN(that.value) || that.value == ".") {
        //case 1: simply entering a number
        if (Calc.Model.newVal == undefined) {
      that.style.color = "blue";
      Calc.Model.newVal = that.value;
      document.getElementById("textRow").value = Calc.Model.oldVal + " " + Calc.Model.operator + " " + Calc.Model.newVal; 
      Calc.Model.highlighted.push(that);
        }
        //case 2: entering a decimal without a previous entry
        else if (Calc.Model.newVal == undefined && that.value == ".") {
          that.style.color = "blue";
          Calc.Model.newVal = 0 + that.value;
          document.getElementById("textRow").value = Calc.Model.oldVal + " " + Calc.Model.operator + " " + Calc.Model.newVal; 
          Calc.Model.highlighted.push(that);
        }
        //case 3: entering a decimal with a previous entry
        else if (Calc.Model.newVal != undefined && that.value == ".") {
          that.style.color = "blue";
          Calc.Model.newVal = Calc.Model.newVal + that.value;
          document.getElementById("textRow").value = Calc.Model.oldVal + " " + Calc.Model.operator + " " + Calc.Model.newVal; 
          Calc.Model.highlighted.push(that);
        //case 4: entering a number when a previous neumber is already defined
        }
        else {
          that.style.color = "blue";
          Calc.Model.newVal = Calc.Model.newVal + that.value; 
          document.getElementById("textRow").value = Calc.Model.oldVal + " " + Calc.Model.operator + " " + Calc.Model.newVal;
          Calc.Model.highlighted.push(that);
        }
      }
    } 
  }
},
//run the function
run : function() {
  Calc.attachHandlers();
  console.log(Calc.display());
  return Calc.display();
},


displayElement : function (element) {
  var s = "<input ";
  s += " id=\"" + element.id + "\"";
  s += " type=\"" + element.type + "\"";
  s += " value= \"" + element.value + "\"";
  s += " onclick= \"" + element.onclick + "\"";
  s += ">";
  return s;

},
//display everything
display : function() {
  var s;
  s = "<table id=\"myTable\" border=2>";
  s += "<tr><td>" + Calc.displayElement(Calc.View.textRow) + "</td></tr>";
  s += "<tr><td>";
  s += Calc.displayElement(Calc.View.button7);
  s += Calc.displayElement(Calc.View.button8);
  s += Calc.displayElement(Calc.View.button9);
  s += Calc.displayElement(Calc.View.buttonAdd);
  s += "<tr><td>";
  s += Calc.displayElement(Calc.View.button4);
  s += Calc.displayElement(Calc.View.button5);
  s += Calc.displayElement(Calc.View.button6);
  s += Calc.displayElement(Calc.View.buttonSub);
  s += "<tr><td>";
  s += Calc.displayElement(Calc.View.button1);
  s += Calc.displayElement(Calc.View.button2);
  s += Calc.displayElement(Calc.View.button3);
  s += Calc.displayElement(Calc.View.buttonMulti);
  s += "<tr><td>";
  s += Calc.displayElement(Calc.View.button0);
  s += Calc.displayElement(Calc.View.buttonDec);
  s += Calc.displayElement(Calc.View.buttonEq);
  s += Calc.displayElement(Calc.View.buttonDiv);
  s += "<tr><td>";
  s += Calc.displayElement(Calc.View.buttonClear);
  s += Calc.displayElement(Calc.View.buttonMR);
  s += Calc.displayElement(Calc.View.buttonMmin);
  s += Calc.displayElement(Calc.View.buttonMAdd);
  s += "<tr><td>";
  s += Calc.displayElement(Calc.View.buttonMC);
  s += "</table>";
  return s;
},
//attatch handlers to buttons
attachHandlers : function() {
   for (var i = 0; i < 10; i++) {
     Calc.View["button" + i].onclick ="Calc.Controller.buttonHandler(this)";
   }
   Calc.View.buttonAdd.onclick = "Calc.Controller.buttonHandler(this)";
   Calc.View.buttonSub.onclick = "Calc.Controller.buttonHandler(this)";
   Calc.View.buttonDiv.onclick = "Calc.Controller.buttonHandler(this)";
   Calc.View.buttonMulti.onclick = "Calc.Controller.buttonHandler(this)";
   Calc.View.buttonEq.onclick = "Calc.Controller.buttonHandler(this)";
   Calc.View.buttonDec.onclick = "Calc.Controller.buttonHandler(this)";
   Calc.View.buttonClear.onclick = "Calc.Controller.buttonHandler(this)";
   Calc.View.buttonMC.onclick = "Calc.Controller.buttonHandler(this)";
   Calc.View.buttonMR.onclick = "Calc.Controller.buttonHandler(this)";
   Calc.View.buttonMAdd.onclick = "Calc.Controller.buttonHandler(this)";
   Calc.View.buttonMmin.onclick = "Calc.Controller.buttonHandler(this)";
},

equals : function(first,operator,second){
  if (operator == "+") {
    return (first*1 + second*1);
  }
   if (operator == "-") {
    return first - second;
  }
   if (operator == "*") {
    return first * second;
  }
   if (operator == "/") {
    return first / second;
  }
},
}; // end of Calc
