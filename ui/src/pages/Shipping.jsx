import React from 'react'
import { useTranslation } from 'react-i18next'
import { api } from '../lib/api'
import { toast } from '../lib/notify'
import { Card, Field, Input, Button, SectionTitle } from '../components/ui'

export default function Shipping() {
  const { t } = useTranslation()
  const [orderId, setOrderId] = React.useState('ORDER-1')
  const [address, setAddress] = React.useState('Berlin, DE')
  const [shipmentId, setShipmentId] = React.useState('')
  const [details, setDetails] = React.useState(null)

  const create = async () => {
    try { const r = await api.post('/shipping/api/shipping', { orderId, address }); const d = r.data?.data || r.data; setShipmentId(d?.id || ''); setDetails(d); toast(t('shipping.created') || 'Shipment created') }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  const pick = async () => {
    try { if (!shipmentId) return; const r = await api.post(`/shipping/api/shipping/${shipmentId}/pick`); setDetails(r.data?.data || r.data) }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  const transit = async () => {
    try { if (!shipmentId) return; const r = await api.post(`/shipping/api/shipping/${shipmentId}/transit`); setDetails(r.data?.data || r.data) }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  const deliver = async () => {
    try { if (!shipmentId) return; const r = await api.post(`/shipping/api/shipping/${shipmentId}/deliver`); setDetails(r.data?.data || r.data) }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  return (
    <div className="grid gap-5">
      <SectionTitle title={t('app.shipping') || 'Shipping'} />
      <Card title={t('shipping.createShipment') || 'Create shipment'}>
        <div className="grid md:grid-cols-12 gap-3 items-end">
          <div className="md:col-span-4"><Field label={t('shipping.orderId') || 'Order ID'}><Input value={orderId} onChange={(e) => setOrderId(e.target.value)} /></Field></div>
          <div className="md:col-span-6"><Field label={t('shipping.address') || 'Address'}><Input value={address} onChange={(e) => setAddress(e.target.value)} /></Field></div>
          <div className="md:col-span-2"><Button onClick={create}>{t('shipping.createShipment') || 'Create'}</Button></div>
        </div>
      </Card>
      <Card title={t('shipping.progress') || 'Progress'}>
        <div className="flex flex-wrap gap-2">
          <Button onClick={pick} variant="outline" disabled={!shipmentId}>{t('shipping.pick') || 'Pick'}</Button>
          <Button onClick={transit} variant="outline" disabled={!shipmentId}>{t('shipping.inTransit') || 'Transit'}</Button>
          <Button onClick={deliver} variant="outline" disabled={!shipmentId}>{t('shipping.delivered') || 'Deliver'}</Button>
        </div>
        <div className="mt-3 text-xs text-zinc-500">Shipment: <code>{shipmentId || '-'}</code></div>
        <pre className="bg-zinc-950 text-green-400 p-4 rounded-xl overflow-auto text-xs mt-3">{JSON.stringify(details, null, 2)}</pre>
      </Card>
    </div>
  )
}
