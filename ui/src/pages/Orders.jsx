import React from 'react'
import { useTranslation } from 'react-i18next'
import { api } from '../lib/api'
import { toast } from '../lib/notify'
import { Card, Field, Input, NumberInput, Button, SectionTitle } from '../components/ui'

export default function Orders() {
  const { t } = useTranslation()
  const [customerId, setCustomerId] = React.useState('CUST-1')
  const [sku, setSku] = React.useState('SKU-1')
  const [qty, setQty] = React.useState(1)
  const [price, setPrice] = React.useState(10)
  const [items, setItems] = React.useState([])
  const [orderId, setOrderId] = React.useState('')
  const [data, setData] = React.useState(null)
  const [loading, setLoading] = React.useState(false)

  const addLine = () => {
    if (!sku || qty <= 0 || price < 0) return toast(t('common.error') || 'Invalid line')
    setItems((prev) => [...prev, { sku, quantity: Number(qty), unitPrice: Number(price) }])
  }

  const create = async () => {
    try {
      if (items.length === 0) return toast(t('orders.addAtLeastOneLine') || 'Add at least one line')
      setLoading(true)
      const total = items.reduce((s, it) => s + it.quantity * it.unitPrice, 0)
      const res = await api.post('/order/api/orders', { customerId, items, total })
      const created = res.data?.data || res.data
      setOrderId(created?.id || created?.orderId || '')
      setData(created)
      toast(t('orders.created') || 'Order created')
    } catch (e) {
      console.error(e)
      toast(t('common.error') || 'Error')
    } finally {
      setLoading(false)
    }
  }

  const pay = async () => {
    try { if (!orderId) return; const r = await api.post(`/order/api/orders/${orderId}/pay`); setData(r.data?.data || r.data) }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  const fulfill = async () => {
    try { if (!orderId) return; const r = await api.post(`/order/api/orders/${orderId}/fulfill`); setData(r.data?.data || r.data) }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  const getOne = async () => {
    try { if (!orderId) return; const r = await api.get(`/order/api/orders/${orderId}`); setData(r.data?.data || r.data) }
    catch (e) { console.error(e); toast(t('common.error') || 'Error') }
  }

  return (
    <div className="grid gap-5">
      <SectionTitle title={t('app.orders') || 'Orders'} actions={<Button onClick={getOne} variant="outline">Refresh</Button>} />
      <Card title={t('orders.create') || 'Create order'}>
        <div className="grid gap-3 md:grid-cols-12">
          <div className="md:col-span-4">
            <Field label={t('orders.customerId') || 'Customer ID'}>
              <Input value={customerId} onChange={(e) => setCustomerId(e.target.value)} />
            </Field>
          </div>
          <div className="md:col-span-8 flex items-end justify-end gap-2">
            <Button onClick={create} disabled={loading}>{t('orders.create') || 'Create'}</Button>
            <Button onClick={pay} variant="outline" disabled={!orderId}>{t('orders.pay') || 'Pay'}</Button>
            <Button onClick={fulfill} variant="outline" disabled={!orderId}>{t('orders.fulfill') || 'Fulfill'}</Button>
          </div>
        </div>
      </Card>
      <Card title={t('orders.addLine') || 'Add lines'} desc={t('orders.buildBeforeCreate') || 'Build the order before creation'}>
        <div className="grid gap-3 md:grid-cols-12 items-end">
          <div className="md:col-span-5"><Field label={t('orders.sku') || 'SKU'}><Input value={sku} onChange={(e) => setSku(e.target.value)} /></Field></div>
          <div className="md:col-span-2"><Field label="Qty"><NumberInput min={1} value={qty} onChange={(e) => setQty(Number(e.target.value))} /></Field></div>
          <div className="md:col-span-3"><Field label="Unit Price"><NumberInput min={0} step="0.01" value={price} onChange={(e) => setPrice(Number(e.target.value))} /></Field></div>
          <div className="md:col-span-2"><Button onClick={addLine} className="w-full">{t('orders.addLine') || 'Add line'}</Button></div>
        </div>
        {items.length > 0 && (
          <div className="mt-4 overflow-auto">
            <table className="min-w-full text-sm">
              <thead className="text-left text-zinc-500">
                <tr><th className="py-2 pr-4">SKU</th><th className="py-2 pr-4">Qty</th><th className="py-2 pr-4">Unit</th></tr>
              </thead>
              <tbody>
                {items.map((it, i) => (
                  <tr key={i} className="border-t border-zinc-100 dark:border-zinc-800">
                    <td className="py-2 pr-4">{it.sku}</td>
                    <td className="py-2 pr-4">{it.quantity}</td>
                    <td className="py-2 pr-4">{it.unitPrice}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </Card>
      <Card title="Order" desc="Server response">
        <div className="text-xs text-zinc-500 mb-2">{t('orders.orderId') || 'Order ID'}: <code>{orderId || '-'}</code></div>
        <pre className="bg-zinc-950 text-green-400 p-4 rounded-xl overflow-auto text-xs">{JSON.stringify(data, null, 2)}</pre>
      </Card>
    </div>
  )
}
