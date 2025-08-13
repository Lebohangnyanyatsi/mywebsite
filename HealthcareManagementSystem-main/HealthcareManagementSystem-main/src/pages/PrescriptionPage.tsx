import React, { useState } from 'react';
import { FileText, Plus, Search, Filter, Edit2, Eye, X, Trash2 } from 'lucide-react';
import Navbar from '../components/layout/Navbar';
import Sidebar from '../components/layout/Sidebar';
import Button from '../components/ui/Button';
import { useAuth } from '../context/AuthContext';
import { prescriptions, patients, doctors } from '../utils/mockData';
import type { Prescription } from '../types';

const PrescriptionsPage: React.FC = () => {
  const { user } = useAuth();
  const [editingPrescription, setEditingPrescription] = useState<Prescription | null>(null);
  const [viewingPrescription, setViewingPrescription] = useState<Prescription | null>(null);
  const [isAddingPrescription, setIsAddingPrescription] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const [showFilters, setShowFilters] = useState(false);
  const [filters, setFilters] = useState({
    startDate: '',
    endDate: '',
    doctorId: '',
    medication: ''
  });
  const [prescriptionToDelete, setPrescriptionToDelete] = useState<Prescription | null>(null);

  const handleSavePrescription = (updatedPrescription: Prescription) => {
    const index = prescriptions.findIndex(p => p.id === updatedPrescription.id);
    if (index !== -1) {
      prescriptions[index] = updatedPrescription;
    }
    setEditingPrescription(null);
  };

  const handleAddPrescription = (newPrescription: Prescription) => {
    prescriptions.push(newPrescription);
    setIsAddingPrescription(false);
  };

  const handleDeletePrescription = () => {
    if (!prescriptionToDelete) return;
    const index = prescriptions.findIndex(p => p.id === prescriptionToDelete.id);
    if (index !== -1) {
      prescriptions.splice(index, 1);
    }
    setPrescriptionToDelete(null);
  };

  const handleFilterChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFilters(prev => ({ ...prev, [name]: value }));
  };

  const resetFilters = () => {
    setFilters({ startDate: '', endDate: '', doctorId: '', medication: '' });
    setShowFilters(false);
  };

  const filteredPrescriptions = prescriptions.filter(prescription => {
    const patient = patients.find(p => p.id === prescription.patientId);
    const searchString = `${patient?.firstName} ${patient?.lastName} ${prescription.medications.map(m => m.name).join(' ')}`.toLowerCase();
    const matchesSearch = searchString.includes(searchTerm.toLowerCase());

    const matchesDateRange = (!filters.startDate || prescription.dateIssued >= filters.startDate) &&
                             (!filters.endDate || prescription.dateIssued <= filters.endDate);

    const matchesDoctor = !filters.doctorId || prescription.doctorId === filters.doctorId;

    const matchesMedication = !filters.medication ||
      prescription.medications.some(m => m.name.toLowerCase().includes(filters.medication.toLowerCase()));

    return matchesSearch && matchesDateRange && matchesDoctor && matchesMedication;
  });

  const renderContent = () => {
    if (viewingPrescription) {
      return (
        <div className="p-6">
          <h2 className="text-xl font-semibold mb-4">View Prescription</h2>
          <p><strong>Patient:</strong> {patients.find(p => p.id === viewingPrescription.patientId)?.firstName} {patients.find(p => p.id === viewingPrescription.patientId)?.lastName}</p>
          <p><strong>Doctor:</strong> Dr. {doctors.find(d => d.id === viewingPrescription.doctorId)?.firstName} {doctors.find(d => d.id === viewingPrescription.doctorId)?.lastName}</p>
          <p><strong>Date Issued:</strong> {new Date(viewingPrescription.dateIssued).toLocaleDateString()}</p>
          <p><strong>Valid Until:</strong> {new Date(viewingPrescription.validUntil).toLocaleDateString()}</p>
          <h3 className="mt-4 font-medium">Medications:</h3>
          <ul className="list-disc ml-6">
            {viewingPrescription.medications.map((med, i) => (
              <li key={i}>{med.name} — {med.dosage}, {med.frequency}</li>
            ))}
          </ul>
          {viewingPrescription.notes && <p className="mt-2"><strong>Notes:</strong> {viewingPrescription.notes}</p>}
          <Button className="mt-4" onClick={() => setViewingPrescription(null)}>Close</Button>
        </div>
      );
    }

    return (
      <div className="overflow-x-auto">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Patient</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Doctor</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Medications</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Date Issued</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Valid Until</th>
              <th className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase">Actions</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {filteredPrescriptions.map((prescription) => {
              const patient = patients.find(p => p.id === prescription.patientId);
              const doctor = doctors.find(d => d.id === prescription.doctorId);
              return (
                <tr key={prescription.id} className="hover:bg-gray-50">
                  <td className="px-6 py-4">{patient?.firstName} {patient?.lastName}</td>
                  <td className="px-6 py-4">Dr. {doctor?.firstName} {doctor?.lastName}</td>
                  <td className="px-6 py-4">{prescription.medications.map(m => m.name).join(', ')}</td>
                  <td className="px-6 py-4">{new Date(prescription.dateIssued).toLocaleDateString()}</td>
                  <td className="px-6 py-4">{new Date(prescription.validUntil).toLocaleDateString()}</td>
                  <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                    <Button variant="outline" size="sm" className="mr-2" leftIcon={<Eye className="h-4 w-4" />} onClick={() => setViewingPrescription(prescription)}>View</Button>
                    <Button variant="outline" size="sm" className="mr-2" leftIcon={<Edit2 className="h-4 w-4" />} onClick={() => setEditingPrescription(prescription)}>Edit</Button>
                    <Button variant="outline" size="sm" className="text-red-600 hover:bg-red-50" leftIcon={<Trash2 className="h-4 w-4" />} onClick={() => setPrescriptionToDelete(prescription)}>Delete</Button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    );
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <div className="flex">
        <Sidebar />
        <main className="flex-1 p-8">
          <div className="max-w-7xl mx-auto">
            <div className="flex justify-between items-center mb-8">
              <div className="flex items-center gap-3">
                <FileText className="h-8 w-8 text-blue-600" />
                <h1 className="text-3xl font-bold text-gray-900">Prescriptions</h1>
              </div>
              <Button leftIcon={<Plus className="h-4 w-4" />} onClick={() => setIsAddingPrescription(true)}>Add New Prescription</Button>
            </div>

            <div className="mb-6 flex flex-col sm:flex-row gap-4">
              <div className="flex-1 relative">
                <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-5 w-5 text-gray-400" />
                <input
                  type="search"
                  placeholder="Search prescriptions..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  className="w-full pl-10 pr-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
              <Button variant="outline" leftIcon={<Filter className="h-4 w-4" />} onClick={() => setShowFilters(!showFilters)}>Filter</Button>
            </div>

            {showFilters && (
              <div className="mb-6 bg-white p-4 rounded-lg shadow-sm border">
                <div className="flex justify-between items-center mb-4">
                  <h3 className="text-lg font-medium text-gray-900">Filters</h3>
                  <Button variant="ghost" size="sm" leftIcon={<X className="h-4 w-4" />} onClick={resetFilters}>Reset</Button>
                </div>
                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">Start Date</label>
                    <input type="date" name="startDate" value={filters.startDate} onChange={handleFilterChange} className="w-full border rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500" />
                  </div>
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">End Date</label>
                    <input type="date" name="endDate" value={filters.endDate} onChange={handleFilterChange} className="w-full border rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500" />
                  </div>
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">Doctor</label>
                    <select name="doctorId" value={filters.doctorId} onChange={handleFilterChange} className="w-full border rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
                      <option value="">All Doctors</option>
                      {doctors.map(doctor => (
                        <option key={doctor.id} value={doctor.id}>Dr. {doctor.firstName} {doctor.lastName}</option>
                      ))}
                    </select>
                  </div>
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">Medication</label>
                    <input type="text" name="medication" value={filters.medication} onChange={handleFilterChange} placeholder="Filter by medication..." className="w-full border rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500" />
                  </div>
                </div>
              </div>
            )}

            <div className="bg-white shadow-md rounded-lg overflow-hidden">
              {renderContent()}
            </div>

            {prescriptionToDelete && (
              <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
                <div className="bg-white rounded-lg p-6 max-w-md w-full mx-4">
                  <h3 className="text-lg font-medium text-gray-900 mb-4">Delete Prescription</h3>
                  <p className="text-gray-500 mb-6">Are you sure you want to delete this prescription? This action cannot be undone.</p>
                  <div className="flex justify-end space-x-4">
                    <Button variant="outline" onClick={() => setPrescriptionToDelete(null)}>Cancel</Button>
                    <Button variant="danger" onClick={handleDeletePrescription}>Delete</Button>
                  </div>
                </div>
              </div>
            )}
          </div>
        </main>
      </div>
    </div>
  );
};

export default PrescriptionsPage;
