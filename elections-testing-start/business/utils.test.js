import * as utils from './utils.js';
import { isValidPassword } from './utils.js';

// Candidate

// Password
/*
Short1!
NoNumber!
NoSpecialCh4r
noc4pitals!
NOL0WER!
goodP4ssw0rd!
*/

describe('isValidPassword', () => {
    test('should return false for password shorter than 8 characters', () => {
      expect(isValidPassword('Short1!')).toBe(false);
    });
  
    test('should return false for password without a number', () => {
      expect(isValidPassword('NoNumber!')).toBe(false);
    });
  
    test('should return false for password without a special character', () => {
      expect(isValidPassword('NoSpecialCh4r')).toBe(false);
    });
  
    test('should return false for password without an uppercase letter', () => {
      expect(isValidPassword('noc4pitals!')).toBe(false);
    });
  
    test('should return false for password without a lowercase letter', () => {
      expect(isValidPassword('NOL0WER!')).toBe(false);
    });
  
    test('should return true for a valid password', () => {
      expect(isValidPassword('goodP4ssw0rd!')).toBe(true);
    });
  });
