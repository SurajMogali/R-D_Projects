var obj1={
    c:100,

};

var obj2={
    c:500,

};




function fnAdd(x,y){
    console.log(x+y+this.c);

}



//By default, this keyword will search for window variable 
//Here for this.c,take obj1's "c" value


//U can use fnAdd.apply() but add in the array format
fnAdd.apply(obj1,[100,200])
fnAdd.call(obj1,100,200)
fnAdd.call(obj2,100,200)

