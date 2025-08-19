import React from 'react'
import { useTranslation } from 'react-i18next'
import { api } from '../lib/api'
import { toast } from '../lib/notify'
import { Card, Field, Input, NumberInput, Button, SectionTitle } from '../components/ui'

export default function Warehouse() {
  const { t } = useTranslation()
  const [location, setLocation] = React.useState('LOC-A')
  const [sku, setSku] = React.useState('SKU-1')
  const [qty, setQty] = React.useState(5)
  const [type, setType] = React.useState('INBOUND')
  const [balance, setBalance] = React.useState(null)

  const move = async () => {
    try { await api.post('/warehouse/api/warehouse/move', { location, sku, qty: Number(qty), type }); toast(t('warehouse.moved') || 'Movement saved'); loadBalance() }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  const loadBalance = async () => {
    try { const r = await api.get('/warehouse/api/warehouse/balance', { params: { location, sku } }); setBalance(r.data?.data ?? r.data ?? 0) }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  return (
    <div className="grid gap-5">
      <SectionTitle title={t('app.warehouse') || 'Warehouse'} />
      <Card title={t('warehouse.movement') || 'Movement'}>
        <div className="grid md:grid-cols-12 gap-3 items-end">
          <div className="md:col-span-4"><Field label={t('warehouse.location') || 'Location'}><Input value={location} onChange={(e) => setLocation(e.target.value)} /></Field></div>
          <div className="md:col-span-4"><Field label="SKU"><Input value={sku} onChange={(e) => setSku(e.target.value)} /></Field></div>
          <div className="md:col-span-2"><Field label="Qty"><NumberInput min={1} value={qty} onChange={(e) => setQty(Number(e.target.value))} /></Field></div>
          <div className="md:col-span-2"><Field label="Type">
            <select value={type} onChange={(e) => setType(e.target.value)} className="h-10 px-3 rounded-xl border border-zinc-300 dark:border-zinc-700 bg-white dark:bg-zinc-900">
              <option value="INBOUND">INBOUND</option>
              <option value="OUTBOUND">OUTBOUND</option>
            </select>
          </Field></div>
        </div>
        <div className="mt-3"><Button onClick={move}>{t('warehouse.apply') || 'Apply movement'}</Button></div>
      </Card>
      <Card title={t('warehouse.balance') || 'Balance'}>
        <div className="flex items-center gap-2">
          <Button variant="outline" onClick={loadBalance}>{t('warehouse.loadBalance') || 'Load'}</Button>
          <div className="text-sm text-zinc-600">{location} / {sku} → <b>{balance ?? '-'}</b></div>
        </div>
      </Card>
    </div>
  )
}
