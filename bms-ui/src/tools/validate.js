function checkNull(type, rule, value, callback) {
  if (!value) {
    return callback(new Error(`${type}不能为空`));
  }
  return callback();
}

export { checkNull }