// let js = "amazing";
// if (js == 'amazing') alert("hi")

console.log(50 + 20 + 88)

let test = true;

console.log(typeof true);

let markWeight = 78;
let markHeight = 1.69;

let BMI = markWeight / markHeight ** 2;
console.log(BMI);



const name = 'pavan';
const age = 23;
const job = 'developer';

const value = `I'm ${name} age ${age} and a ${job}`;
console.log(value);

console.log('String with \n\multiple lines ')


const ages = 4;
ages >= 18 ? console.log("major") :
    console.log("minor");


const vote = age >= 18 ? 'major' : 'minor';
console.log(vote);

console.log(`I am a ${age >= 18 ? 'majors' : 'minor'}`)


const bill = 40;

const tip = bill >= 50 && bill <= 300 ? bill * (15 / 100) : bill * (20 / 100);

const total = bill + tip;

const totalValue = `total bill value is ${total} out of which tip is ${tip} and actual bill amount is ${bill}`;

console.log(totalValue);