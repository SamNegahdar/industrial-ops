export const toast = (msg) => (window?.toast ? window.toast(msg) : alert(msg))
