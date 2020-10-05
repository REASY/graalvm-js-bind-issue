console.log("########### Direct call in JS #############")
console.log("window.document.createElement:", window.document.createElement("p"));
console.log("document.createElement.apply:", window.document.createElement.apply(window.document, ["p"]));
console.log("########################")

console.log("\n")

console.log("########## Call via Bind/Call ##############")
var boundCall = Object.bind.apply(Object.call, [Object.bind, Object.call]);
var boundCall2 = boundCall.apply(window, [Object.bind]);
var createElementMethod = boundCall2.apply(window, [document.createElement, window.document]);
console.log(createElementMethod("p"));
console.log(createElementMethod.apply(window.document, ["p"]));
console.log("########################")
