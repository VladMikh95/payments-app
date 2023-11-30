package ml.vladmikh.projects.payments_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ml.vladmikh.projects.hotel_app.databinding.PaymentItemBinding
import ml.vladmikh.projects.payments_app.data.network.model.Payment

class PaymentAdapter: ListAdapter<Payment, PaymentAdapter.PaymentViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Payment>() {
            override fun areItemsTheSame(oldItem: Payment, newItem: Payment): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Payment, newItem: Payment): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val viewHolder = PaymentViewHolder(
            PaymentItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {


        holder.bind(getItem(position))
    }

    class PaymentViewHolder(private var binding: PaymentItemBinding): RecyclerView.ViewHolder(binding.root) {

        val titleValue = binding.titleValueTextView
        val amountValue = binding.amountValueTextView
        val created = binding.createdValueTextView

        fun bind(payment: Payment) {
            titleValue.text = payment.title
            amountValue.text = payment.amount
            created.text = payment.created
        }


    }
}