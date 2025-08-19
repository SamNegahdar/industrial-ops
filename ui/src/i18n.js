import i18n from 'i18next'
import { initReactI18next } from 'react-i18next'

const resources = {
  en: {
    translation: {
      app: { title: 'Industrial Ops Suite', orders:'Orders', inventory:'Inventory', production:'Production', warehouse:'Warehouse', shipping:'Shipping' },
      common: { error: 'Error' },
      orders: {
        create: 'Create', customerId: 'Customer ID', addAtLeastOneLine:'Add at least one line',
        created:'Order created', addLine:'Add line', pay:'Pay', fulfill:'Fulfill', buildBeforeCreate:'Build the order before creation', orderId:'Order ID'
      },
      inventory: {
        stock:'Stock', stockDesc:'Create/update SKU stock', apply:'Apply', reserve:'Reserve', release:'Release', commit:'Commit',
        saved:'Saved', reserved:'Reserved', released:'Released', committed:'Committed', load:'Load SKUs'
      },
      production: { created:'Work order created', createWo:'Create', start:'Start', complete:'Complete' },
      warehouse: { moved:'Movement saved', movement:'Movement', apply:'Apply movement', loadBalance:'Load', balance:'Balance', location:'Location', sku:'SKU' },
      shipping: { created:'Shipment created', createShipment:'Create', orderId:'Order ID', address:'Address', progress:'Progress', pick:'Pick', inTransit:'Transit', delivered:'Deliver' }
    }
  },
  fa: {
    translation: {
      app: { title: 'مجموعه عملیات صنعتی', orders:'سفارش‌ها', inventory:'انبار', production:'تولید', warehouse:'انبارش', shipping:'حمل‌ونقل' },
      common: { error: 'خطا' },
      orders: {
        create: 'ایجاد', customerId:'شناسه مشتری', addAtLeastOneLine:'حداقل یک آیتم اضافه کنید',
        created:'سفارش ایجاد شد', addLine:'افزودن آیتم', pay:'پرداخت', fulfill:'ارسال', buildBeforeCreate:'پیش از ایجاد، سفارش را بسازید', orderId:'شناسه سفارش'
      },
      inventory: {
        stock:'موجودی', stockDesc:'ثبت/بروزرسانی موجودی SKU', apply:'ثبت', reserve:'رزرو', release:'آزادسازی', commit:'تایید',
        saved:'ذخیره شد', reserved:'رزرو شد', released:'آزاد شد', committed:'تایید شد', load:'بارگذاری SKUها'
      },
      production: { created:'WO ایجاد شد', createWo:'ایجاد', start:'شروع', complete:'تکمیل' },
      warehouse: { moved:'جابجایی ثبت شد', movement:'جابجایی', apply:'اعمال جابجایی', loadBalance:'بارگذاری', balance:'تراز', location:'محل', sku:'SKU' },
      shipping: { created:'مرسوله ایجاد شد', createShipment:'ایجاد', orderId:'شناسه سفارش', address:'نشانی', progress:'پیشرفت', pick:'جمع‌آوری', inTransit:'در مسیر', delivered:'تحویل' }
    }
  }
}

i18n.use(initReactI18next).init({
  resources,
  lng: 'fa',
  fallbackLng: 'en',
  interpolation: { escapeValue: false }
})

export default i18n
