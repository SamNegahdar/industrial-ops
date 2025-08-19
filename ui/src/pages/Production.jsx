import React from 'react'
import { useTranslation } from 'react-i18next'
import { api } from '../lib/api'
import { toast } from '../lib/notify'
import { Card, Field, Input, NumberInput, Button, SectionTitle } from '../components/ui'

export default function Production() {
  const { t } = useTranslation()
  const [sku, setSku] = React.useState('SKU-1')
  const [qty, setQty] = React.useState(2)
  const [woId, setWoId] = React.useState('')
  const [details, setDetails] = React.useState(null)

  const create = async () => {
    try { const r = await api.post('/production/api/production/work-orders', { productSku: sku, quantity: Number(qty) }); const d = r.data?.data || r.data; setWoId(d?.id || ''); setDetails(d); toast(t('production.created') || 'Work order created') }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }
  const start = async () => {
    try { if (!woId) return; const r = await api.post(`/production/api/production/work-orders/${woId}/start`); setDetails(r.data?.data || r.data) }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }
  const complete = async () => {
    try { if (!woId) return; const r = await api.post(`/production/api/production/work-orders/${woId}/complete`); setDetails(r.data?.data || r.data) }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  return (
    <div className="grid gap-5">
      <SectionTitle title={t('app.production') || 'Production'} />
      <Card title="Work order">
        <div className="grid md:grid-cols-12 gap-3 items-end">
          <div className="md:col-span-6"><Field label="Product SKU"><Input value={sku} onChange={(e) => setSku(e.target.value)} /></Field></div>
          <div className="md:col-span-3"><Field label="Qty"><NumberInput min={1} value={qty} onChange={(e) => setQty(Number(e.target.value))} /></Field></div>
          <div className="md:col-span-3 flex gap-2"><Button onClick={create}>{t('production.createWo') || 'Create'}</Button><Button onClick={start} variant="outline" disabled={!woId}>{t('production.start') || 'Start'}</Button><Button onClick={complete} variant="outline" disabled={!woId}>{t('production.complete') || 'Complete'}</Button></div>
        </div>
        <div className="text-xs text-zinc-500 mt-2">WO: <code>{woId || '-'}</code></div>
        <pre className="bg-zinc-950 text-green-400 p-4 rounded-xl overflow-auto text-xs mt-3">{JSON.stringify(details, null, 2)}</pre>
      </Card>
    </div>
  )
}
