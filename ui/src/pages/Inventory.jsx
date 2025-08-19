import React from 'react'
import { useTranslation } from 'react-i18next'
import { api } from '../lib/api'
import { toast } from '../lib/notify'
import { Card, Field, Input, NumberInput, Button, SectionTitle } from '../components/ui'

export default function Inventory() {
  const { t } = useTranslation()
  const [sku, setSku] = React.useState('SKU-1')
  const [available, setAvailable] = React.useState(10)
  const [qty, setQty] = React.useState(5)
  const [reservationId, setReservationId] = React.useState('')
  const [skus, setSkus] = React.useState([])

  const listSkus = async () => {
    try { const r = await api.get('/inventory/api/inventory/sku'); setSkus(r.data?.data || r.data || []) }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  const upsert = async () => {
    try { await api.post('/inventory/api/inventory/sku', { code: sku, available: Number(available) }); toast(t('inventory.saved') || 'Saved'); listSkus() }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  const reserve = async () => {
    try { const r = await api.post('/inventory/api/inventory/reserve', { sku, qty: Number(qty) }); setReservationId(r.data?.data?.id || r.data?.id || ''); toast(t('inventory.reserved') || 'Reserved'); listSkus() }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  const release = async () => {
    try { if (!reservationId) return; await api.post(`/inventory/api/inventory/reserve/${reservationId}/release`); setReservationId(''); toast(t('inventory.released') || 'Released'); listSkus() }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  const commit = async () => {
    try { if (!reservationId) return; await api.post(`/inventory/api/inventory/reserve/${reservationId}/commit`); setReservationId(''); toast(t('inventory.committed') || 'Committed'); listSkus() }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  return (
    <div className="grid gap-5">
      <SectionTitle title={t('app.inventory') || 'Inventory'} actions={<Button onClick={listSkus} variant="outline">Refresh</Button>} />
      <Card title={t('inventory.stock') || 'Stock'} desc={t('inventory.stockDesc') || 'Create/update SKU stock'}>
        <div className="grid md:grid-cols-12 gap-3 items-end">
          <div className="md:col-span-6"><Field label="SKU"><Input value={sku} onChange={(e) => setSku(e.target.value)} /></Field></div>
          <div className="md:col-span-3"><Field label="Available"><NumberInput min={0} value={available} onChange={(e) => setAvailable(Number(e.target.value))} /></Field></div>
          <div className="md:col-span-3"><Button onClick={upsert} className="w-full">{t('inventory.apply') || 'Save'}</Button></div>
        </div>
      </Card>
      <Card title={t('inventory.reserve') || 'Reserve'} desc="Reserve / release / commit">
        <div className="grid md:grid-cols-12 gap-3 items-end">
          <div className="md:col-span-5"><Field label="SKU"><Input value={sku} onChange={(e) => setSku(e.target.value)} /></Field></div>
          <div className="md:col-span-3"><Field label="Qty"><NumberInput min={1} value={qty} onChange={(e) => setQty(Number(e.target.value))} /></Field></div>
          <div className="md:col-span-4 flex gap-2">
            <Button onClick={reserve}>{t('inventory.reserve') || 'Reserve'}</Button>
            <Button onClick={release} variant="outline" disabled={!reservationId}>{t('inventory.release') || 'Release'}</Button>
            <Button onClick={commit} variant="outline" disabled={!reservationId}>{t('inventory.commit') || 'Commit'}</Button>
          </div>
        </div>
        <div className="text-xs text-zinc-500 mt-2">Reservation: <code>{reservationId || '-'}</code></div>
      </Card>
      <Card title="SKUs">
        <div className="overflow-auto">
          <table className="min-w-full text-sm">
            <thead className="text-left text-zinc-500"><tr><th className="py-2 pr-4">Code</th><th className="py-2 pr-4 text-right">Available</th><th className="py-2 pr-4 text-right">Reserved</th></tr></thead>
            <tbody>
              {skus.map((s,i)=> (
                <tr key={i} className="border-t border-zinc-100 dark:border-zinc-800">
                  <td className="py-2 pr-4">{s.code}</td>
                  <td className="py-2 pr-4 text-right">{s.available}</td>
                  <td className="py-2 pr-4 text-right">{s.reserved}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </Card>
    </div>
  )
}
