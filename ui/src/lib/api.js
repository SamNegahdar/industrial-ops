import axios from 'axios'
const base = (typeof import.meta !== 'undefined' && import.meta.env && import.meta.env.VITE_API_BASE_URL) || ''
export const api = axios.create({ baseURL: base })
