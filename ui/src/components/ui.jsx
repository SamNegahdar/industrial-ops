import React from 'react'

export const Card = ({ title, desc, children, className = '' }) => (
  <section className={`rounded-2xl shadow-sm border border-zinc-200 dark:border-zinc-800 bg-white dark:bg-zinc-900 ${className}`}>
    {(title || desc) && (
      <header className="px-5 pt-4 pb-2 border-b border-zinc-100 dark:border-zinc-800">
        {title && <h3 className="text-lg font-semibold">{title}</h3>}
        {desc && <p className="text-sm text-zinc-500 mt-1">{desc}</p>}
      </header>
    )}
    <div className="p-5">{children}</div>
  </section>
)

export const Field = ({ label, children, hint }) => (
  <label className="flex flex-col gap-1">
    {label && <span className="text-sm text-zinc-600 dark:text-zinc-300">{label}</span>}
    {children}
    {hint && <span className="text-xs text-zinc-400">{hint}</span>}
  </label>
)

export const Input = (props) => (
  <input
    {...props}
    className={`h-10 px-3 rounded-xl border border-zinc-300 dark:border-zinc-700 bg-white dark:bg-zinc-900 outline-none focus:ring-2 focus:ring-indigo-500 ${props.className || ''}`}
  />
)

export const NumberInput = (props) => <Input type="number" step="1" {...props} />

export const Button = ({ children, variant = 'primary', className = '', ...rest }) => {
  const base = 'h-10 px-4 rounded-xl text-sm font-medium transition-colors'
  const variants = {
    primary: 'bg-indigo-600 hover:bg-indigo-700 text-white',
    outline: 'border border-zinc-300 dark:border-zinc-700 hover:bg-zinc-50 dark:hover:bg-zinc-800',
    ghost: 'hover:bg-zinc-100 dark:hover:bg-zinc-800',
    danger: 'bg-rose-600 hover:bg-rose-700 text-white',
  }
  return (
    <button className={`${base} ${variants[variant] || ''} ${className}`} {...rest}>
      {children}
    </button>
  )
}

export const SectionTitle = ({ title, actions }) => (
  <div className="flex items-center justify-between mb-3">
    <h2 className="text-xl font-semibold">{title}</h2>
    <div className="flex gap-2">{actions}</div>
  </div>
)
