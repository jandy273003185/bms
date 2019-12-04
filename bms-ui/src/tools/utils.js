export const storage = {
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