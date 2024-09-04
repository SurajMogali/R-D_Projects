//Synchronous Callback

function greet(name) {
    console.log('Hello ' + name);
}



function processUserInput(fn) {
    var name = 'John';
    fn(name);
}

// Higher order function. If it is called to be Higher Order Function,then it should be take atleast one argument as Function
//Inside the bracket, it is called Callback function 
processUserInput(greet); // Output: Hello John


// Asynchronous Callback
function fetchData(callback) {
    setTimeout(function() {
        var data = 'Data from server';
        callback(data);
    }, 1000);
}

fetchData(function(data) {
    console.log(data); // Output: Data from server
});

//Function Expression
var a=function print()
{
    console.log("Hello Bharath") ;
}

a();

