export const lstorage = {
  set: (key, value) => {
    if (typeof value === 'object' && value !== null) {
      localStorage.setItem(key, JSON.stringify(value));
      return;
    }
    localStorage.setItem(key, value);
  },
  get: (key) => {
    return JSON.parse(localStorage.getItem(key));
  }
}

export const sstorage = {
  set: (key, value) => {
    if (typeof value === 'object' && value !== null) {
      sessionStorage.setItem(key, JSON.stringify(value));
      return;
    }
    sessionStorage.setItem(key, value);
  },
  get: (key) => {
    return JSON.parse(sessionStorage.getItem(key));
  }
}

export const preview = function (file) {
  const fileReader = new FileReader();
  let src = '';
  if (URL.createObjectURL) {
    src = URL.createObjectURL(file);
  } else {
    src = fileReader.readAsDataURL(file);
  }
  return src;
}