import React from 'react'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import Orders from './pages/Orders.jsx'
import Inventory from './pages/Inventory.jsx'
import Production from './pages/Production.jsx'
import Warehouse from './pages/Warehouse.jsx'
import Shipping from './pages/Shipping.jsx'

export default function App() {
  const [dark, setDark] = React.useState(false)
  React.useEffect(() => {
    document.documentElement.classList.toggle('dark', dark)
    document.body.className = dark ? 'bg-zinc-950 text-zinc-100' : 'bg-zinc-50 text-zinc-900'
  }, [dark])

  return (
    <BrowserRouter>
      <header className="sticky top-0 z-10 backdrop-blur bg-white/70 dark:bg-zinc-950/70 border-b border-zinc-200 dark:border-zinc-800">
        <div className="max-w-6xl mx-auto px-4 py-3 flex items-center justify-between">
          <div className="font-extrabold text-lg">Industrial Ops Suite</div>
          <nav className="flex gap-3">
            <Link to="/orders">Orders</Link>
            <Link to="/inventory">Inventory</Link>
            <Link to="/production">Production</Link>
            <Link to="/warehouse">Warehouse</Link>
            <Link to="/shipping">Shipping</Link>
          </nav>
          <button className="border rounded-xl px-3 h-9" onClick={() => setDark(d => !d)}>{dark ? 'Light' : 'Dark'}</button>
        </div>
      </header>

      <main className="max-w-6xl mx-auto px-4 py-6 grid gap-6">
        <Routes>
          <Route path="/" element={<Orders />} />
          <Route path="/orders" element={<Orders />} />
          <Route path="/inventory" element={<Inventory />} />
          <Route path="/production" element={<Production />} />
          <Route path="/warehouse" element={<Warehouse />} />
          <Route path="/shipping" element={<Shipping />} />
        </Routes>
      </main>
    </BrowserRouter>
  )
}
