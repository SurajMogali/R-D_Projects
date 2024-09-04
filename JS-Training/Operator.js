//Pre-increment operator

var x=1;
console.log(x);

++x;
console.log(x);

//Post-increment operator

var y=100
y++
console.log(y)    

// In Javascript,=== will check the value and datatype 

var details=[
    {
        brand:"Apple",
        price:90000,
        model:"Iphone11"
    },
    {
        brand:"Apple",
        price:70000,
        model:"Iphone12"

    },
    {
        brand:"vivo",
        price:90000,
        mode:"Iphone13"

    },
    {
        brand:"Moto",
        price:90000,
        mode:"G84"

    },
];

var filteredData=details.filter(function(element)
{
    return (element.brand=="Apple" || element.brand=="vivo") && element.price > 70000

});

console.log(filteredData);










 

