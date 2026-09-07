from flask import Flask, render_template, request
import requests

app = Flask(__name__)

CURRENCIES = {
    "USD": "US Dollar", "INR": "Indian Rupee", "EUR": "Euro", "GBP": "British Pound",
    "JPY": "Japanese Yen", "AUD": "Australian Dollar", "CAD": "Canadian Dollar",
    "SGD": "Singapore Dollar", "CHF": "Swiss Franc", "CNY": "Chinese Yuan"
}
API_URL = "https://api.frankfurter.app/latest"

@app.route("/", methods=["GET"])
def home():
    return render_template("index.html", currencies=CURRENCIES, result=None, error=None,
                           amount="", from_currency="USD", to_currency="INR")

@app.route("/convert", methods=["POST"])
def convert():
    amount_text = request.form.get("amount", "").strip()
    from_currency = request.form.get("from_currency", "USD").upper()
    to_currency = request.form.get("to_currency", "INR").upper()
    try:
        amount = float(amount_text)
        if amount < 0:
            raise ValueError("Amount cannot be negative.")
        if from_currency not in CURRENCIES or to_currency not in CURRENCIES:
            raise ValueError("Please select valid currencies.")
        if from_currency == to_currency:
            converted = amount
        else:
            response = requests.get(API_URL, params={"amount": amount, "from": from_currency, "to": to_currency}, timeout=10)
            response.raise_for_status()
            data = response.json()
            if to_currency not in data.get("rates", {}):
                raise ValueError("Exchange rate was not returned.")
            converted = float(data["rates"][to_currency])
        result = f"{amount:.2f} {from_currency} = {converted:.2f} {to_currency}"
        return render_template("index.html", currencies=CURRENCIES, result=result, error=None,
                               amount=amount_text, from_currency=from_currency, to_currency=to_currency)
    except ValueError as exc:
        return render_template("index.html", currencies=CURRENCIES, result=None, error=str(exc),
                               amount=amount_text, from_currency=from_currency, to_currency=to_currency), 400
    except requests.RequestException:
        return render_template("index.html", currencies=CURRENCIES, result=None,
                               error="Exchange-rate service is unavailable. Check your internet connection.",
                               amount=amount_text, from_currency=from_currency, to_currency=to_currency), 503

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=9090, debug=False)
