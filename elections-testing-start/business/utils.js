function isValidCandidate(candidate) {
  const errors = [];

  if (!candidate.name || typeof candidate.name !== 'string' || candidate.name.length < 2) {
    errors.push('Name must be a string with at least 2 characters.');
  }

  if (candidate.title && (typeof candidate.title !== 'string' || candidate.title.length < 2)) {
    errors.push('Title must be a string with at least 2 characters.');
  }

  return errors.length > 0 ? { valid: false, errors } : { valid: true };
}

function isValidPassword(pw) {
  /*
    require password to have (using positive look-ahead):
    *number         \d
    *lower case     [a-z]
    *special char   [!@#$%^&&]
    *at least 8 characters
    *positive look-ahead: (?= )


  */
  const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[ !"#$%&'()*+,\-.\/:;<=>?@[\\\]^_`{|}~]).{8,}$/;

  return passwordRegex.test(pw);
}

export { 
  isValidCandidate,
  isValidPassword
};
