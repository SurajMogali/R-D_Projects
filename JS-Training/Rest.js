//Rest operator is used to store all the remaining arguments in the array format
//Rest parameter should be passed as a last parameter


//Even if u pass 5 parameters to ...data, it will take and print it.

function f1(x,y,...data){
    console.log(x,y,data);
}

//f1(100,"Dummy Value");

f1(100,150,200,300,500);