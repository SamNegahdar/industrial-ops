import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/order':      { target: 'http://localhost:8080', changeOrigin: true },
      '/inventory':  { target: 'http://localhost:8080', changeOrigin: true },
      '/production': { target: 'http://localhost:8080', changeOrigin: true },
      '/warehouse':  { target: 'http://localhost:8080', changeOrigin: true },
      '/shipping':   { target: 'http://localhost:8080', changeOrigin: true }
    }
  }
})
