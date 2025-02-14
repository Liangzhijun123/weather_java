import * as utils from './business/utils.js';

console.log( utils.isValidCandidate( { foo: `bar` } ) );
console.log( utils.isValidCandidate( { name: `x` } ) );
console.log( utils.isValidCandidate( { name: `George` } ) );
console.log( utils.isValidCandidate( { name: `George`, title: `x` } ) );
console.log( utils.isValidCandidate( { name: `George`, title: `MD` } ) );

console.log();
console.log( utils.isValidPassword( `Short1!` ) );
console.log( utils.isValidPassword( `NoNumber!` ) );
console.log( utils.isValidPassword( `NoSpecialCh4r` ) );
console.log( utils.isValidPassword( `noc4pitals!` ) );
console.log( utils.isValidPassword( `NOL0WER!` ) );
console.log( utils.isValidPassword( `goodP4ssw0rd!` ) );